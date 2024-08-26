package uoa.lavs.utils.objects;

import uoa.lavs.models.Addresses;
import uoa.lavs.models.Emails;
import uoa.lavs.models.Phones;

public record ContactInfo(Addresses addresses, Phones phones, Emails emails) {}
