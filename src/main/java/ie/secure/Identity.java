package ie.secure;

import java.util.UUID;
import javax.annotation.Generated;

public class Identity {
	
	private String name;
	private UUID uniqueIdentifier;
	private String userName;
	private String email;
	private String mobileNumber;
	
	public String getName() {
		return name;
	}
	public UUID getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	public String getUserName() {
		return userName;
	}
	public String getEmail() {
		return email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	@Generated("SparkTools")
	private Identity(Builder builder) {
		this.name = builder.name;
		this.uniqueIdentifier = builder.uniqueIdentifier;
		this.userName = builder.userName;
		this.email = builder.email;
		this.mobileNumber = builder.mobileNumber;
	}
	/**
	 * Creates builder to build {@link Identity}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link Identity}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String name;
		private UUID uniqueIdentifier;
		private String userName;
		private String email;
		private String mobileNumber;

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withUniqueIdentifier(UUID uniqueIdentifier) {
			this.uniqueIdentifier = uniqueIdentifier;
			return this;
		}

		public Builder withUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}

		public Identity build() {
			return new Identity(this);
		}
	}
	
	
	

}
