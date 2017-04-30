package xzx.core.permision;

import xzx.nsfw.user.entity.User;

public interface PermisionCheck {

	public boolean isAccessible(User user, String string);

}
