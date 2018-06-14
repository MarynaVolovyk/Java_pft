package ru.stqa.pft.adressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Contacts extends ForwardingSet<NewContact> {

    private Set<NewContact> delegate;

    public Contacts(Contacts contacts) { this.delegate = new HashSet<NewContact>(contacts.delegate);   }

    public Contacts() { this.delegate = new HashSet<NewContact>(); }

    public Contacts(Collection<NewContact> contacts) {
      this.delegate = new HashSet<NewContact>(contacts);
    }

  @Override
    protected Set<NewContact> delegate() {
      return delegate;
    }

    public Contacts withAdded(NewContact contact) {
      Contacts contacts = new Contacts(this);
      contacts.add(contact);
      return contacts;
    }
    public Contacts without(NewContact contact) {
      Contacts contacts = new Contacts(this);
      contacts.remove(contact);
      return contacts;
    }
}


