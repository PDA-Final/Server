package com.pda.kafkautils;

public class KafkaUtils {
    public static String getJsonTypeMappingInfo(Class<? extends KafkaJson> ...clazzs) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> clazz : clazzs) {
            sb.append(clazz.getSimpleName());
            sb.append(":");
            sb.append(clazz.getName());
            sb.append(",");
        }

        sb.setLength(sb.length()-1);

        return sb.toString();
    }
}
