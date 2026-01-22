package entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
 
@Entity
public class Emissions extends PanacheEntityBase {
     
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String categoryName;

    public String gasUnits;

    public String description;   

    public String NK;
    
    public int year;            

    public double value;    
    
    public String scenario;    

    public String sourceType;    

    public boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    public User approvedBy;
}
