package se.citerus.dddsample.tracking.core.domain.shared;

/**
 * An entity, as explained in the DDD book.
 */
public interface Entity<T> {

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return true if the identities are the same, regardles of other attributes.
   */
  boolean sameAs(T other);

}