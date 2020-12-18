package Models;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactID;

    @ManyToOne
    @JoinColumn(name="contact_id", nullable = false)
    private Contact ownerContact;

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public Contact getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(Contact ownerContact) {
        this.ownerContact = ownerContact;
    }
}
