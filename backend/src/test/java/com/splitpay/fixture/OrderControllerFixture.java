package com.splitpay.fixture;

public class OrderControllerFixture {

  public static String jsonExampleComplete() {
    return "{\n" +
        "    \"participants\": [\n" +
        "      {\n" +
        "        \"name\": \"Juliano\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Hamburguer\",\n" +
        "            \"value\": 40\n" +
        "          },\n" +
        "          {\n" +
        "            \"name\": \"Sobremesa\",\n" +
        "            \"value\": 2\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"Amigo\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Sanduíche\",\n" +
        "            \"value\": 8\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ],\n" +
        "     \"increases\":[\n" +
        "        {\n" +
        "            \"name\": \"frete\",\n" +
        "            \"value\": 8,\n" +
        "            \"isPercentage\": false\n" +
        "          }\n" +
        "    ],\n" +
        "     \"discounts\":[\n" +
        "        {\n" +
        "            \"name\": \"cupomDesconto\",\n" +
        "            \"value\": 20,\n" +
        "            \"isPercentage\":false\n" +
        "          }\n" +
        "    ]\n" +
        "}";
  }

  public static String jsonExampleWithoutIncreses() {
    return "{\n" +
        "    \"participants\": [\n" +
        "      {\n" +
        "        \"name\": \"Juliano\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Hamburguer\",\n" +
        "            \"value\": 40\n" +
        "          },\n" +
        "          {\n" +
        "            \"name\": \"Sobremesa\",\n" +
        "            \"value\": 2\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"Amigo\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Sanduíche\",\n" +
        "            \"value\": 8\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ],\n" +
        "     \"discounts\":[\n" +
        "        {\n" +
        "            \"name\": \"cupomDesconto\",\n" +
        "            \"value\": 20,\n" +
        "            \"isPercentage\":false\n" +
        "          }\n" +
        "    ]\n" +
        "}\n";
  }

  public static String jsonExampleWithoutDiscounts() {
    return "{\n" +
        "    \"participants\": [\n" +
        "      {\n" +
        "        \"name\": \"Juliano\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Hamburguer\",\n" +
        "            \"value\": 40\n" +
        "          },\n" +
        "          {\n" +
        "            \"name\": \"Sobremesa\",\n" +
        "            \"value\": 2\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"Amigo\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Sanduíche\",\n" +
        "            \"value\": 8\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ],\n" +
        "     \"increases\":[\n" +
        "        {\n" +
        "            \"name\": \"frete\",\n" +
        "            \"value\": 8,\n" +
        "            \"isPercentage\": false\n" +
        "          }\n" +
        "    ]\n" +
        "}";
  }

  public static String jsonExampleWithoutNeagtiveValue() {
    return "{\n" +
        "    \"participants\": [\n" +
        "      {\n" +
        "        \"name\": \"Juliano\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Hamburguer\",\n" +
        "            \"value\": -40\n" +
        "          },\n" +
        "          {\n" +
        "            \"name\": \"Sobremesa\",\n" +
        "            \"value\": 2\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"Amigo\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Sanduíche\",\n" +
        "            \"value\": 8\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ],\n" +
        "     \"increases\":[\n" +
        "        {\n" +
        "            \"name\": \"frete\",\n" +
        "            \"value\": 8,\n" +
        "            \"isPercentage\": false\n" +
        "          }\n" +
        "    ]\n" +
        "}";
  }

  public static String jsonExampleWith1Participant() {
    return "{\n" +
        "    \"participants\": [\n" +
        "      {\n" +
        "        \"name\": \"Juliano\",\n" +
        "        \"items\": [\n" +
        "          {\n" +
        "            \"name\": \"Hamburguer\",\n" +
        "            \"value\": 40\n" +
        "          },\n" +
        "          {\n" +
        "            \"name\": \"Sobremesa\",\n" +
        "            \"value\": 2\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ]\n" +
        "}\n";
  }
  public static String jsonResponseExample1() {
    return "{\"paymentLinks\":{\"Amigo\":\"https://picpay.me/Amigo/6.08\",\"Juliano\":\"https://picpay.me/Juliano/31.92\"}}";
  }

  public static String jsonResponseWithoutIncreases(){
    return "{\"paymentLinks\":{\"Amigo\":\"https://picpay.me/Amigo/4.80\",\"Juliano\":\"https://picpay.me/Juliano/25.20\"}}";
  }

  public static String jsonResponseWithoutDiscounts(){
    return "{\"paymentLinks\":{\"Amigo\":\"https://picpay.me/Amigo/9.28\",\"Juliano\":\"https://picpay.me/Juliano/48.72\"}}";
  }

}
