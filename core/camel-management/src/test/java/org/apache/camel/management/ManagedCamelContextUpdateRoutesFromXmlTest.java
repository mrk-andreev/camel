/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.management;

import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagedCamelContextUpdateRoutesFromXmlTest extends ManagementTestSupport {

    @Test
    public void testDumpAsXml() throws Exception {
        // JMX tests dont work well on AIX CI servers (hangs them)
        if (isPlatform("aix")) {
            return;
        }

        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedBodiesReceived("Hello World");
        template.sendBody("direct:start", "Hello World");
        assertMockEndpointsSatisfied();

        MBeanServer mbeanServer = getMBeanServer();

        // there should be 1 routes to start with
        Set<ObjectName> set = mbeanServer.queryNames(new ObjectName("*:type=routes,*"), null);
        assertEquals(1, set.size());

        // update existing route, and add a 2nd
        String xml = "<routes id=\"myRoute\" xmlns=\"http://camel.apache.org/schema/spring\">"
                     + "<route id=\"myRoute\">"
                     + "  <from uri=\"direct:start\"/>"
                     + "  <log message=\"This is a changed route saying ${body}\"/>"
                     + "  <to uri=\"mock:changed\"/>"
                     + "</route>"
                     + "<route id=\"myOtherRoute\">"
                     + "  <from uri=\"seda:bar\"/>"
                     + "  <to uri=\"mock:bar\"/>"
                     + "</route>"
                     + "</routes>";

        ObjectName on = ObjectName.getInstance("org.apache.camel:context=camel-1,type=context,name=\"camel-1\"");
        mbeanServer.invoke(on, "addOrUpdateRoutesFromXml", new Object[] { xml }, new String[] { "java.lang.String" });

        // there should be 2 routes now
        set = mbeanServer.queryNames(new ObjectName("*:type=routes,*"), null);
        assertEquals(2, set.size());

        // test updated route
        getMockEndpoint("mock:changed").expectedMessageCount(1);
        template.sendBody("direct:start", "Bye World");
        assertMockEndpointsSatisfied();

        // test new route
        getMockEndpoint("mock:bar").expectedMessageCount(1);
        template.sendBody("seda:bar", "Hi Camel");
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start").routeId("myRoute")
                        .log("Got ${body}")
                        .to("mock:result");
            }
        };
    }

}
