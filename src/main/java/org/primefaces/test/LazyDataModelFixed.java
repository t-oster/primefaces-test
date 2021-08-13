package org.primefaces.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import javax.faces.model.DataModelListener;
import org.primefaces.model.LazyDataModel;

/**
 * The org.primefaces.model.LazyDataModel has a deserialization problem as
 * of 10.0.0 
 * 
 * https://github.com/primefaces/primefaces/issues/7699 and
 * https://github.com/joinfaces/joinfaces/issues/1159
 * 
 * The problem is that on deserialization the default constructor on the first
 * non-serializable superclass (javax.faces.model.ListDataModel) is called,
 * which sets the list attribute (wrappedData) to null.
 * This class saves and restores it on serialization/deserialization and can
 * be used as drop-in replacement for LazyDataModel
 * @author Thomas Oster <thomas.oster@upstart-it.de>
 */
public abstract class LazyDataModelFixed<T> extends LazyDataModel<T> {

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException 
    {
        aInputStream.defaultReadObject();
        setWrappedData(aInputStream.readObject());
        setRowIndex(aInputStream.readInt());
        DataModelListener[] l = (DataModelListener[]) aInputStream.readObject();
        if (l != null) {
            for (DataModelListener li : l) {
                addDataModelListener(li);
            }
        }
    }
 
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.defaultWriteObject();
        List wd = getWrappedData();
        if (wd != null) {
            //wrap in LinkedList because implementation may not be serializable
            wd = new LinkedList<>(wd);
        }
        aOutputStream.writeObject(wd);
        aOutputStream.writeInt(getRowIndex());
        aOutputStream.writeObject(getDataModelListeners());
    }
    
}
