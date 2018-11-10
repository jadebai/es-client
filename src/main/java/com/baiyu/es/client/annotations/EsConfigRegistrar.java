package com.baiyu.es.client.annotations;

import com.baiyu.es.client.model.EsClient;
import com.baiyu.es.client.model.EsClientDto;
import com.baiyu.es.client.util.RegisterBeanDefinitionUtil;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author baiyu
 * @description: EsConfigRegistrar
 * @date: 2018/11/2
 */
@Slf4j
public class EsConfigRegistrar implements ImportBeanDefinitionRegistrar {

    public EsConfigRegistrar() {
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        log.info("EsConfigRegistrar start register!!!!");
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableEsConfig.class.getName()));
        String socketAddressStrings = annotationAttributes.getString("socketAddressStrings");
        String clusterName = annotationAttributes.getString("clusterName");
        String sniff = annotationAttributes.getString("sniff");
        log.info("Annotation param clusterName:{}:", clusterName);
        log.info("Annotation param addresses:{}:", socketAddressStrings);
        log.info("Annotation param sniff:{}:", sniff);
        EsClientDto esClientDto = new EsClientDto();
        esClientDto.setClusterName(clusterName);
        esClientDto.setSniff(sniff);
        esClientDto.setSocketAddressStrings(socketAddressStrings);
        checkEsClientDto(esClientDto);
        EsAnnotationProcessor.initEsClientDto(esClientDto);
        doRegister(registry);
    }

    private void doRegister(BeanDefinitionRegistry registry) {
        RegisterBeanDefinitionUtil.registerBeanDefinitionIfNotExists(registry,EsClient.class.getName(),EsClient.class);
        RegisterBeanDefinitionUtil.registerBeanDefinitionIfNotExists(registry,EsAnnotationProcessor.class.getName(),EsAnnotationProcessor.class);
    }

    private void checkEsClientDto(EsClientDto esClientDto) {
        Preconditions.checkNotNull(esClientDto, "esClientDto cannot be null");
        Preconditions.checkNotNull(esClientDto.getSocketAddressStrings(), "es.addresses cannot be null");
        Preconditions.checkNotNull(esClientDto.getClusterName(), "es.cluster.name cannot be null");
        Preconditions.checkNotNull(esClientDto.getSniff(), "client.transport.sniff cannot be null");
    }
}
