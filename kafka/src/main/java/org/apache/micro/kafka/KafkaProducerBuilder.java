package org.apache.micro.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by HuangYueWen on 2016-11-12.
 */
public class KafkaProducerBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerBuilder.class) ;

    private Properties prop ;

    public KafkaProducerBuilder() {
        this.prop = new Properties() ;
    }

    /**
     *
     * @param server host:port,host1:port1...
     * @return
     */
    public KafkaProducerBuilder setBootstrapServer(String server){
        prop.setProperty(KafkaProducerConstants.BOOTSTRAP_SERVER,server) ;
        return this ;
    }
    public KafkaProducerBuilder setKeySerializerType(SerializerType stype){
        prop.setProperty(KafkaProducerConstants.KEY_SERIALIZER,stype.name()) ;
        return this ;
    }

    public KafkaProducer build(){
        KafkaProducer producer = new KafkaProducer(this.prop) ;
        return producer;
    }

    public KafkaProducer getKafkaProducer(Properties properties){
        return  new KafkaProducer(properties) ;
    }

    public enum SerializerType{
        KRYO("kryo") ;

        private String name ;

        SerializerType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args){
        SerializerType stype = SerializerType.KRYO ;
        System.out.println(stype.name()) ;
        System.out.println(stype.getName()) ;
    }

}
