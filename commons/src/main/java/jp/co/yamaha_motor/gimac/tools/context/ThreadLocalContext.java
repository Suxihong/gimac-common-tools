package jp.co.yamaha_motor.gimac.tools.context;

import java.util.List;

public class ThreadLocalContext {
    private static final ThreadLocal<String> operationStatus = new ThreadLocal<>();
    private static final ThreadLocal<String> onlineStatus = new ThreadLocal<>();
    private static final ThreadLocal<List<String>> AvailableModels = new ThreadLocal<>();

    public static void setOperationStatus(String operation) {
        operationStatus.set(operation);
    }

    public static String getOperationStatus() {
        return operationStatus.get();
    }
    public static void setOnlineStatus(String online) {
        onlineStatus.set(online);
    }

    public static String getOnlineStatus() {
        return onlineStatus.get();
    }

    public static void setAvailableModels(List<String> models) {
        AvailableModels.set(models);
    }

    public static List<String> getAvailableModels() {
        return AvailableModels.get();
    }
    public static void clear() {
        operationStatus.remove();
        onlineStatus.remove();
        AvailableModels.remove();
    }
}
