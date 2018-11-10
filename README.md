# es-client
封装的ES-Client，引入即可使用
使用方法：
1.maven方式引入jar包
        <dependency>
            <groupId>com.baiyu.es</groupId>
            <artifactId>es-client</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
2.在config文件上加上@EnableEsConfig(clusterName = "${search.cluster.name}",socketAddressStrings = "${search.es.addresses}")
  参数意义：1.clusterName：集群名称 
           2.socketAddressStrings：ES地址-支持集群采用英文逗号隔开；
                 例如：172.21.247.89:9300,172.21.247.89:9300
3.所有跟ES相对于的实体类  都必须继承父类：EsModel；  
      例如：class DemoEsModel extends EsModel
4.所有ES实体类对应的查询类  都需要继承抽象类：AbstractEsSearchHandler；  
      例如：class DemoEsSearchHandler extends AbstractEsSearchHandler<DemoEsModel>
5.使用：SearchResp<DemoEsModel> searchResp = EsSearchHandlerFactory.getHandler(DemoEsModel.class).search(searchParam);
      查询参数需要组装SearchParam对象
