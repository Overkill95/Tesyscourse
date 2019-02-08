package it.dao;

import it.dto.DtoObject;

public class DaoInterfaceImpl implements DaoInterface {

	@Override
	public DtoObject getDtoObject() {
		// TODO Auto-generated method stub
		return new DtoObject("Infoc DAO");
	}

}
