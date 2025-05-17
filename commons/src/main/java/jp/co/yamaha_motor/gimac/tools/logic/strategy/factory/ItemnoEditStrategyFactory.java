package jp.co.yamaha_motor.gimac.tools.logic.strategy.factory;

import java.util.HashMap;
import java.util.Map;

import jp.co.yamaha_motor.gimac.tools.logic.strategy.DefaultItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.ItemnoEditStrategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClass1Strategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClass2Strategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClass3Strategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClass5Strategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClass7Strategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClassEStrategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClassFStrategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClassKStrategy;
import jp.co.yamaha_motor.gimac.tools.logic.strategy.impl.ItemClassMStrategy;

public class ItemnoEditStrategyFactory {
    private static final Map<String, ItemnoEditStrategy> strategies = new HashMap<>();

    static {
        strategies.put("M", new ItemClassMStrategy());
        strategies.put("E", new ItemClassEStrategy());
        strategies.put("K", new ItemClassKStrategy());
        strategies.put("F", new ItemClassFStrategy());
        strategies.put("3", new ItemClass3Strategy());
        strategies.put("5", new ItemClass5Strategy());
        strategies.put("7", new ItemClass7Strategy());
        strategies.put("2", new ItemClass2Strategy());
        strategies.put("1", new ItemClass1Strategy());
        strategies.put("DEFAULT", new DefaultItemnoEditStrategy());
    }

    public static ItemnoEditStrategy getStrategy(String itemClass) {
        return strategies.getOrDefault(itemClass, strategies.get("DEFAULT"));
    }
}
