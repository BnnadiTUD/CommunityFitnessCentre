package entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
 
@Entity
public class Emissions extends PanacheEntityBase {
   
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = true)
    public String categoryCode;

    public String categoryName;

    public String description;   

    public int year;            

    public double value;    
    
    public String scenario;    

    @Column(nullable = false)
    public String sourceType;    

    public boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    public User approvedBy;
}
