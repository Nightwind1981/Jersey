package de.rf4.server;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import de.rf4.interfaces.IFangbuch;

@Stateless
@Remote(value = IFangbuch.class)
public class Fanbuchserver implements IFangbuch
{
    
}
