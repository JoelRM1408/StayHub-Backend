package com.example.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
	private String image;
    private Boolean actsubcription;
    
	public UserDTO(String name, String lastname, String phone, String email) {
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.email = email;
	} 

}
