package com.udh.common.presentation.domain.value_object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestAttribute {
    // Các trươờng lấy từ header của request
    REQ_HEAD_X_CLIENT_SOURCE("X-Client-Source", "Nguồn gửi request"),

    // Các truường set vào request attribute
    REQ_ATTR_REQUEST_ID("request_id", "Id định danh request"),
    REQ_ATTR_USER_SOURCE("usrc", "User được get ra từ nguồn nào"),
    REQ_ATTR_IAM_TOKEN("IamToken", "Token IamId"),
    REQ_ATTR_IAM_USER("IamUser", "User lấy được từ Iam"),
    REQ_ATTR_IAM_CLIENT_ID("IamClientId", "ClientId của token"),
    REQ_ATTR_EXTRA_DATA("ex_data.", "Data log mở rộng"),
    REQ_ATTR_EXTRA_FIELD("ex_field.", "Field log thêm vào request");

    public final String key;
    public final String name;

    public static RequestAttribute tryFrom(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }

        for (RequestAttribute item : RequestAttribute.values()) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }
}
