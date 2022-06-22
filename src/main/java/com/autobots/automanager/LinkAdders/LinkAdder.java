package com.autobots.automanager.LinkAdders;


import java.util.List;


public interface LinkAdder<T> {
	public void addLink(List<T> lista);
	public void addLink(T objeto);
}
