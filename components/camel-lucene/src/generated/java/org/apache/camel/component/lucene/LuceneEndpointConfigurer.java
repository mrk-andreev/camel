/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.lucene;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@SuppressWarnings("unchecked")
public class LuceneEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        LuceneEndpoint target = (LuceneEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "analyzer": target.getConfig().setAnalyzer(property(camelContext, org.apache.lucene.analysis.Analyzer.class, value)); return true;
        case "indexdir":
        case "indexDir": target.getConfig().setIndexDir(property(camelContext, java.io.File.class, value)); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        case "maxhits":
        case "maxHits": target.getConfig().setMaxHits(property(camelContext, int.class, value)); return true;
        case "srcdir":
        case "srcDir": target.getConfig().setSrcDir(property(camelContext, java.io.File.class, value)); return true;
        case "basicpropertybinding":
        case "basicPropertyBinding": target.setBasicPropertyBinding(property(camelContext, boolean.class, value)); return true;
        case "synchronous": target.setSynchronous(property(camelContext, boolean.class, value)); return true;
        default: return false;
        }
    }

}
