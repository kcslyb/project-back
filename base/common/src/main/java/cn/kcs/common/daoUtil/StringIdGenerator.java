package cn.kcs.common.daoUtil;

import org.tinygroup.tinysqldsl.KeyGenerator;
import org.tinygroup.tinysqldsl.base.InsertContext;

public class StringIdGenerator implements KeyGenerator {
    @Override
    public <T> T generate(InsertContext insertContext) {
        return (T) ShortUUID.generate();
    }
}
