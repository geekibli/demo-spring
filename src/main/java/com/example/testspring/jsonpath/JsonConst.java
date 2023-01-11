package com.example.testspring.jsonpath;

public class JsonConst {

    public static final String store = "{\n" +
            "    \"store\": {\n" +
            "        \"book\": [\n" +
            "            {\n" +
            "                \"category\": \"reference\",\n" +
            "                \"author\": \"Nigel Rees\",\n" +
            "                \"title\": \"Sayings of the Century\",\n" +
            "                \"price\": 8.95\n" +
            "            },\n" +
            "            {\n" +
            "                \"category\": \"fiction\",\n" +
            "                \"author\": \"Evelyn Waugh\",\n" +
            "                \"title\": \"Sword of Honour\",\n" +
            "                \"price\": 12.99\n" +
            "            },\n" +
            "            {\n" +
            "                \"category\": \"fiction\",\n" +
            "                \"author\": \"Herman Melville\",\n" +
            "                \"title\": \"Moby Dick\",\n" +
            "                \"isbn\": \"0-553-21311-3\",\n" +
            "                \"price\": 8.99\n" +
            "            },\n" +
            "            {\n" +
            "                \"category\": \"fiction\",\n" +
            "                \"author\": \"J. R. R. Tolkien\",\n" +
            "                \"title\": \"The Lord of the Rings\",\n" +
            "                \"isbn\": \"0-395-19395-8\",\n" +
            "                \"price\": 22.99\n" +
            "            }\n" +
            "        ],\n" +
            "        \"bicycle\": {\n" +
            "            \"color\": \"red\",\n" +
            "            \"price\": 19.95\n" +
            "        }\n" +
            "    },\n" +
            "    \"expensive\": 10\n" +
            "}";


    public static String followReq1 = "{\"orderCenterId\":\"101011292490496514\",\"remark\":\"测试v1\",\"selectList\":[{\"id\":11,\"name\":\"微信\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":1,\"parentModuleName\":\"联系方式\"},{\"id\":13,\"name\":\"其他\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":1,\"parentModuleName\":\"联系方式\"},{\"id\":14,\"name\":\"正常提车\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":2,\"parentModuleName\":\"交付意向\"},{\"id\":18,\"name\":\"是\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":3,\"parentModuleName\":\"是否存在问题\"},{\"id\":28,\"name\":\"全款购车\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":6,\"parentModuleName\":\"购车方式\"},{\"id\":32,\"name\":\"线上支付\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":7,\"parentModuleName\":\"尾款付款方式\"},{\"id\":38,\"name\":\"店内购买\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":8,\"parentModuleName\":\"保险服务\"},{\"id\":42,\"name\":\"店内上牌\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":9,\"parentModuleName\":\"上牌服务\"},{\"id\":46,\"name\":\"购买家充桩\",\"levelType\":2,\"inputType\":1,\"parentModuleId\":10,\"parentModuleName\":\"充电桩服务\"},{\"id\":52,\"name\":\"意向提车日期\",\"levelType\":2,\"inputType\":4,\"parentModuleId\":0,\"value\":\"2022-12-03\"}],\"contactType\":\"微信,其他\"}";
}
