package org.primefaces.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    }
 
    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.defaultWriteObject();
        aOutputStream.writeObject(this.getWrappedData());
    }
}
