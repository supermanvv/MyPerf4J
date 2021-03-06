package cn.myperf4j.core;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by LinShunkang on 2018/5/20
 */
public class TagMaintainer extends AbstractTagMaintainer {

    public static final int MAX_NUM = 1024 * 512;

    private final AtomicInteger tagIndex = new AtomicInteger(0);

    private final AtomicReferenceArray<String> tagArr = new AtomicReferenceArray<>(MAX_NUM);

    private static final TagMaintainer instance = new TagMaintainer();

    private TagMaintainer() {
        //empty
    }

    public static TagMaintainer getInstance() {
        return instance;
    }

    @Override
    public int addTag(String tag) {
        int tagId = tagIndex.getAndIncrement();
        if (tagId > MAX_NUM) {
            return -1;
        }

        tagArr.set(tagId, tag);
        return tagId;
    }

    @Override
    public String getTag(int tagId) {
        if (tagId >= 0 && tagId < MAX_NUM) {
            return tagArr.get(tagId);
        }
        return null;
    }

}
