package com.entities; 

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Table(name = "users")
@Entity
public class User implements UserDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    
    //qst costruttori non ci sono sul tutorial, togliere se vedo che non mi servono piu
	public User(Integer id, String fullName, String email, String password, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public User() {
	}
	//
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	 public User setFullName(String fullName) {
	        this.fullName = fullName;
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
