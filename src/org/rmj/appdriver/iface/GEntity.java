/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rmj.appdriver.iface;

/**
 * RMJ POJO Interface
 * @author kalyptus
 */
public interface GEntity {
    public Object getValue(int fnColumn);
    public Object getValue(String fsColumn);
    public String getTable();
    public String getColumn(int fnCol);
    public int getColumn(String fsCol);
    public void setValue(int fnColumn, Object foValue);
    public void setValue(String fsColumn, Object foValue);
    public int getColumnCount();
    public void list();
}


