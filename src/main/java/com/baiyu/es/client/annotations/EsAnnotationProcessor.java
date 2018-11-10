package com.baiyu.es.client.annotations;

import com.baiyu.es.client.exception.EsErrorCode;
import com.baiyu.es.client.exception.EsException;
import com.baiyu.es.client.model.EsClient;
import com.baiyu.es.client.model.EsClientDto;
import com.baiyu.es.client.util.PlacehodlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyu
 * @description: EsAnnotationProcessor
 * @date: 2018/11/5
 */
@Slf4j
public class EsAnnotationProcessor implements BeanPostProcessor,EnvironmentAware {

    private final static EsClientDto ES_CLIENT_DTO = new EsClientDto();

    private ConfigurableEnvironment environment;

    public static void initEsClientDto(final EsClientDto esClientDto){
        ES_CLIENT_DTO.setClusterName(esClientDto.getClusterName());
        ES_CLIENT_DTO.setSocketAddressStrings(esClientDto.getSocketAddressStrings());
        ES_CLIENT_DTO.setSniff(esClientDto.getSniff());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof EsClient){
            ((EsClient) bean).setClient(initClient());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment)environment;
    }


    private Client initClient() {
        convertEsClientDto();
        log.info("es client is start init params : addresses:{},clusterName:{},sniff:{}",
                ES_CLIENT_DTO.getSocketAddressStrings(),ES_CLIENT_DTO.getClusterName(),ES_CLIENT_DTO.getSniff());
        Settings settings = Settings.settingsBuilder().put("cluster.name", ES_CLIENT_DTO.getClusterName())
                //嗅探机制开启,目的是为了可以找到集群
                .put("client.transport.sniff", ES_CLIENT_DTO.getSniff()).build();
        List<TransportAddress> socketAddressList = new ArrayList<>();
        String[] address = ES_CLIENT_DTO.getSocketAddressStrings().split(",");
        for (String socketAddressString : address) {
            try {
                if (StringUtils.isEmpty(socketAddressString)){
                    continue;
                }
                String[] socketAddressSplit = StringUtils.split(socketAddressString, ":");
                socketAddressList.add(new InetSocketTransportAddress(InetAddress.getByName(socketAddressSplit[0]), Integer.parseInt(socketAddressSplit[1])));
            } catch (NumberFormatException | UnknownHostException e) {
                log.info("es client init... ,addresses is error:{}", e.getMessage());
                throw new EsException(EsErrorCode.ES_ADDRESSES_ERROR);
            }
        }
        Client client = TransportClient.builder().settings(settings).build().addTransportAddresses(socketAddressList.toArray(new TransportAddress[address.length]));
        Assert.notNull(client, "es client init fail : is null");
        log.info("es client init success ,client is :{}", client);
        return client;
    }

    private void convertEsClientDto() {
        ES_CLIENT_DTO.setClusterName(PlacehodlerUtil.convertPlacehodler(ES_CLIENT_DTO.getClusterName(),environment));
        ES_CLIENT_DTO.setSocketAddressStrings(PlacehodlerUtil.convertPlacehodler(ES_CLIENT_DTO.getSocketAddressStrings(),environment));
        ES_CLIENT_DTO.setSniff(PlacehodlerUtil.convertPlacehodler(ES_CLIENT_DTO.getSniff(),environment));
    }
}
