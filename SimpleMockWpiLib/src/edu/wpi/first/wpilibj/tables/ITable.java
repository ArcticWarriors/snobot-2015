package edu.wpi.first.wpilibj.tables;

public interface ITable {

	void putBoolean(String string, boolean enabled);

	void addTableListener(String string, ITableListener m_table_listener, boolean b);

	void removeTableListener(ITableListener m_table_listener);

	void putNumber(String string, double rate);

	void putString(String string, String string2);

}
