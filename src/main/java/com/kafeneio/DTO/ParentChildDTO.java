package com.kafeneio.DTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class ParentChildDTO implements UserDetails,CredentialsContainer {
	private Long Id;
	private Long parentId;
	private String position ;
	private String sponsorId;
	private RegistrationDTO registration;
	
	private Set<GrantedAuthority> authorities;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public RegistrationDTO getRegistration() {
		return registration;
	}
	public void setRegistration(RegistrationDTO registration) {
		this.registration = registration;
	}
	public String getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}
	@Override
	public void eraseCredentials() {
		 //password = null;
		registration.getLogin().setPassword(null);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return registration.getLogin().getEmail();
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return  registration.getLogin().getPassword();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	private static SortedSet<GrantedAuthority> sortAuthorities(
		      Collection<? extends GrantedAuthority> authorities) {
		    Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		    // Ensure array iteration order is predictable (as per
		    // UserDetails.getAuthorities() contract and SEC-717)
		    SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(
		        new AuthorityComparator());

		    for (GrantedAuthority grantedAuthority : authorities) {
		      Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
		      sortedAuthorities.add(grantedAuthority);
		    }

		    return sortedAuthorities;
		  }
	
	  private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

	    /** The Constant serialVersionUID. */
	    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	    /*
	     * (non-Javadoc)
	     * 
	     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	     */
	    public int compare(GrantedAuthority g1, GrantedAuthority g2) {
	      // Neither should ever be null as each entry is checked before adding it to
	      // the set.
	      // If the authority is null, it is a custom authority and should precede
	      // others.
	      if (g2.getAuthority() == null) {
	        return -1;
	      }

	      if (g1.getAuthority() == null) {
	        return 1;
	      }

	      return g1.getAuthority().compareTo(g2.getAuthority());
	    }
	  }

}
