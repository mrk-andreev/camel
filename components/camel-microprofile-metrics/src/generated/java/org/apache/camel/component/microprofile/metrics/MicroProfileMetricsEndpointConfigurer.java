/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.microprofile.metrics;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@SuppressWarnings("unchecked")
public class MicroProfileMetricsEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        MicroProfileMetricsEndpoint target = (MicroProfileMetricsEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "action": target.setAction(property(camelContext, java.lang.String.class, value)); return true;
        case "basicpropertybinding":
        case "basicPropertyBinding": target.setBasicPropertyBinding(property(camelContext, boolean.class, value)); return true;
        case "counterincrement":
        case "counterIncrement": target.setCounterIncrement(property(camelContext, java.lang.Long.class, value)); return true;
        case "description": target.setDescription(property(camelContext, java.lang.String.class, value)); return true;
        case "displayname":
        case "displayName": target.setDisplayName(property(camelContext, java.lang.String.class, value)); return true;
        case "gaugedecrement":
        case "gaugeDecrement": target.setGaugeDecrement(property(camelContext, java.lang.Boolean.class, value)); return true;
        case "gaugeincrement":
        case "gaugeIncrement": target.setGaugeIncrement(property(camelContext, java.lang.Boolean.class, value)); return true;
        case "gaugevalue":
        case "gaugeValue": target.setGaugeValue(property(camelContext, java.lang.Number.class, value)); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        case "mark": target.setMark(property(camelContext, java.lang.Long.class, value)); return true;
        case "metricunit":
        case "metricUnit": target.setMetricUnit(property(camelContext, java.lang.String.class, value)); return true;
        case "synchronous": target.setSynchronous(property(camelContext, boolean.class, value)); return true;
        case "tags": target.setTags(property(camelContext, java.lang.String.class, value)); return true;
        case "value": target.setValue(property(camelContext, java.lang.Long.class, value)); return true;
        default: return false;
        }
    }

}
