package org.cent.ApiDemo.configuration;

import org.cent.ApiDemo.model.pojo.Item;
import org.cent.ApiDemo.model.pojo.ItemTemplate;
import org.cent.ApiDemo.util.ItemParserUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

/**
 * 接口配置
 * @author Vincent
 * @version 1.0 2019/8/3
 */
@Configuration
public class ItemConfiguration {

    static private Logger LOGGER = LoggerFactory.getLogger(ItemConfiguration.class);

    @Bean
    ItemTemplate itemTemplate() throws DocumentException {
        return new ItemTemplate(loadDdz());
    }

    private HashMap<String, Item> loadDdz() throws DocumentException {
        HashMap<String, Item> itemHashMap = new HashMap<>();
        String ddzName = "/ddz/";
        LOGGER.info(String.format("=============开始加载[%s]接口配置=============", ddzName));

        URL ddzUrl = this.getClass().getResource(ddzName);
        if (ddzUrl == null) {
            LOGGER.info(String.format("接口配置资源异常：%s", ddzName));
            return itemHashMap;
        }

        File ddz = new File(ddzUrl.getFile());
        if (!ddz.exists()) {
            LOGGER.info(String.format("接口配置资源不存在：%s", ddzName));
            return itemHashMap;
        }
        if (!ddz.isDirectory()) {
            LOGGER.info(String.format("接口配置资源非目录：%s", ddzName));
            return itemHashMap;
        }

        File[] xml = ddz.listFiles(pathname -> pathname.getName().endsWith(".xml"));
        if (xml == null) {
            LOGGER.info(String.format("接口配置资源未定义：%s", ddzName));
            return itemHashMap;
        }

        for (File file : xml) {
            Item item = ItemParserUtil.parseXml(this.getClass().getResource(ddzName+file.getName()));
            itemHashMap.put(item.getName(), item);
            LOGGER.info(String.format("载入接口[%s]", file.getName()));
        }
        LOGGER.info(String.format("=============加载[%s]接口配置完成=============", ddzName));
        return itemHashMap;
    }
}
