package com.goinghugh.event;

import com.goinghugh.model.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

/**
 * <p>用户创建事件</p>
 *
 * @author wangyongxu
 * @date 2019/6/19 18:42
 */
public class UserCreateEvent extends ApplicationEvent {

    public UserCreateEvent(User source) {
        super(source);
    }

    @Override
    @EventListener
    public User getSource() {
        return (User) super.getSource();
    }
}
