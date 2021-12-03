package com.saum.session;

import lombok.*;

/**
 * @Author saum
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;

    @Override
    public String toString() {
        return userId + ":" + userName;
    }
}
