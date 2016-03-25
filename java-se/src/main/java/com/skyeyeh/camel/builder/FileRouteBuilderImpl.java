package com.skyeyeh.camel.builder;

import com.skyeyeh.camel.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TV6015 on 2016/3/22.
 */
public class FileRouteBuilderImpl extends RouteBuilder {
    final Logger logger = LoggerFactory.getLogger(FileRouteBuilderImpl.class);

    /**
     * 每 1 秒執行一次，印出日誌。
     *
     * @throws Exception 執行錯誤
     */
    @Override
    public void configure() throws Exception {
        BindyDataFormat bindyDataFormat = new BindyDataFormat();
        bindyDataFormat.setType(BindyType.Fixed);
        bindyDataFormat.setLocale("zh");
        bindyDataFormat.setClassType(Order.class);

        from("file:order.txt").unmarshal(bindyDataFormat).log("log:OUTPUT FOR FIXED LENGTH UNMARHSALLING EXAMPLE ::: ${body}");
    }
}
