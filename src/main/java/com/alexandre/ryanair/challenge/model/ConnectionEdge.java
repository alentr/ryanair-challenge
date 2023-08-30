package com.alexandre.ryanair.challenge.model;

import org.jgrapht.graph.DefaultEdge;

public class ConnectionEdge extends DefaultEdge {

  protected Airport getFrom() {
    return (Airport) super.getSource();
  }

  protected Airport getTo() {
    return (Airport) super.getTarget();
  }

  @Override
  public String toString() {
    return "Connection{" + "from='" + getSource() + '\'' + ", to='" + getTarget() + '\'' + '}';
  }
}
