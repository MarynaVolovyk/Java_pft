package ru.stqa.pft.adressbook.model;

import com.google.common.collect.ForwardingSet;
import java.util.HashSet;
import java.util.Set;


public class Contacts extends ForwardingSet<NewContact> {

    private Set<NewContact> delegate;
    public Contacts(Contacts contacts) { this.delegate = new HashSet<NewContact>(contacts.delegate);   }

    public Contacts() { this.delegate = new HashSet<NewContact>(); }

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


