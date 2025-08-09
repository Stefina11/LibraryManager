package com.entities; 

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import enums.Role;
import jakarta.persistence.*;

@Table(name = "users")
@Entity
public class User implements UserDetails {
		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)  
    @Column(nullable = false)
    private Role role;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name())); 
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	 public User setFirstName(String firstName) {
	        this.firstName = firstName;
	        return this;
	    }
	 
	 public String getLastName() {
		 return lastName;
	 }

	 public User setLastName(String lastName) {
	        this.lastName = lastName;
	        return this;
	    }

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
        this.email = email;
        return this;
    }

	public String getPassword() {
		return password;
	}
	
    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }
	
	@Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


	public Date getCreatedAt() {
		return createdAt;
	}

	 public User setCreatedAt(Date createdAt) {
	        this.createdAt = createdAt;
	        return this;
	    }

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public User setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
	
    public User setPassword(String password) {
        this.password = password;
        return this;
    }



}
