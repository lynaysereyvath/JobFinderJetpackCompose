package com.example.jobfinder.data.remote


import com.google.gson.annotations.SerializedName

data class JobDetailDataDto(
    @SerializedName("apply_options")
    val applyOptions: List<ApplyOption>?,
    @SerializedName("employer_company_type")
    val employerCompanyType: String?,
    @SerializedName("employer_linkedin")
    val employerLinkedin: Any?,
    @SerializedName("employer_logo")
    val employerLogo: String?,
    @SerializedName("employer_name")
    val employerName: String?,
    @SerializedName("employer_reviews")
    val employerReviews: List<EmployerReview>?,
    @SerializedName("employer_website")
    val employerWebsite: String?,
    @SerializedName("estimated_salaries")
    val estimatedSalaries: List<EstimatedSalary>?,
    @SerializedName("job_apply_is_direct")
    val jobApplyIsDirect: Boolean?,
    @SerializedName("job_apply_link")
    val jobApplyLink: String?,
    @SerializedName("job_apply_quality_score")
    val jobApplyQualityScore: Double?,
    @SerializedName("job_benefits")
    val jobBenefits: List<String>?,
    @SerializedName("job_city")
    val jobCity: String?,
    @SerializedName("job_country")
    val jobCountry: String?,
    @SerializedName("job_description")
    val jobDescription: String?,
    @SerializedName("job_employment_type")
    val jobEmploymentType: String?,
    @SerializedName("job_experience_in_place_of_education")
    val jobExperienceInPlaceOfEducation: Boolean?,
    @SerializedName("job_google_link")
    val jobGoogleLink: String?,
    @SerializedName("job_highlights")
    val jobHighlights: JobHighlights?,
    @SerializedName("job_id")
    val jobId: String?,
    @SerializedName("job_is_remote")
    val jobIsRemote: Boolean?,
    @SerializedName("job_job_title")
    val jobJobTitle: String?,
    @SerializedName("job_latitude")
    val jobLatitude: Double?,
    @SerializedName("job_longitude")
    val jobLongitude: Double?,
    @SerializedName("job_max_salary")
    val jobMaxSalary: Int?,
    @SerializedName("job_min_salary")
    val jobMinSalary: Int?,
    @SerializedName("job_naics_code")
    val jobNaicsCode: String?,
    @SerializedName("job_naics_name")
    val jobNaicsName: String?,
    @SerializedName("job_occupational_categories")
    val jobOccupationalCategories: Any?,
    @SerializedName("job_offer_expiration_datetime_utc")
    val jobOfferExpirationDatetimeUtc: Any?,
    @SerializedName("job_offer_expiration_timestamp")
    val jobOfferExpirationTimestamp: Any?,
    @SerializedName("job_onet_job_zone")
    val jobOnetJobZone: String?,
    @SerializedName("job_onet_soc")
    val jobOnetSoc: String?,
    @SerializedName("job_posted_at_datetime_utc")
    val jobPostedAtDatetimeUtc: String?,
    @SerializedName("job_posted_at_timestamp")
    val jobPostedAtTimestamp: Int?,
    @SerializedName("job_posting_language")
    val jobPostingLanguage: String?,
    @SerializedName("job_publisher")
    val jobPublisher: String?,
    @SerializedName("job_required_education")
    val jobRequiredEducation: JobRequiredEducation?,
    @SerializedName("job_required_experience")
    val jobRequiredExperience: JobRequiredExperience?,
    @SerializedName("job_required_skills")
    val jobRequiredSkills: Any?,
    @SerializedName("job_salary_currency")
    val jobSalaryCurrency: Any?,
    @SerializedName("job_salary_period")
    val jobSalaryPeriod: Any?,
    @SerializedName("job_state")
    val jobState: String?,
    @SerializedName("job_title")
    val jobTitle: String?
)