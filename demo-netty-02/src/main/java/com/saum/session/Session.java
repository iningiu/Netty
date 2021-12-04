package com.saum.session;

import lombok.*;

import java.io.Serializable;

/**
 * @Author saum
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session implements Serializable {
    private String userId;
    private String userName;

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
