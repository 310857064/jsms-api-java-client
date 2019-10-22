package cn.jsms.api.sign;

import cn.jiguang.common.utils.Preconditions;
import cn.jiguang.common.utils.StringUtils;
import cn.jsms.api.common.model.IModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;

public class SignPayload implements IModel {

    private static String SIGN = "sign";
    private static String IMAGES = "images";
    private static String TYPE = "type";
    private static String REMARK = "remark";

    private String sign;
    private File[] images;
    private String remark;
    private Integer type;

    private static Gson gson = new Gson();

    public SignPayload(String sign, File[] images, String remark, Integer type) {
        this.sign = sign;
        this.images = images;
        this.remark = remark;
        this.type = type;
    }

    private SignPayload(Builder builder) {
        sign = builder.sign;
        images = builder.images;
        remark = builder.remark;
        type = builder.type;
    }

    public static final class Builder {
        private String sign;
        private File[] images;
        private String remark;
        private Integer type;

        public Builder() {
        }

        public Builder sign(String val) {
            sign = val;
            return this;
        }

        public Builder images(File[] val) {
            images = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder type(Integer val) {
            type = val;
            return this;
        }

        public SignPayload build() {
            Preconditions.checkArgument(!StringUtils.isEmpty(sign), "sign should be not empty");
            Preconditions.checkArgument(type > 0 && type < 7, "type should be between 1 and 7");
            return new SignPayload(this);
        }
    }


    public JsonElement toJSON() {
        JsonObject json = new JsonObject();

        if (!StringUtils.isEmpty(sign)) {
            json.addProperty(SIGN, sign);
        }

        if (type > 0 && type <7) {
            json.addProperty(TYPE, type);
        }

        if (remark != null) {
            json.addProperty(REMARK, remark);
        }
        return json;
    }

    @Override
    public String toString() {
        return gson.toJson(toJSON());
    }
}
