package it.simple.app.dao;

import java.util.List;

import it.simple.app.dto.UserInfo;

public interface SimpleDaoInterface {
	public abstract List<UserInfo> getUserInfo(String name);
}
