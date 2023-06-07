import csv


with open ("zillow_with_images.csv", 'r') as file1, open("zillow_with_images_cleaned.csv", "w") as file2:
    reader = csv.reader(file1)
    reader = csv.DictReader(file1)
    writer = csv.DictWriter(file2, fieldnames=["id", "ScrapperOrder", "Link", "ApartmentName", "Address", "RentStudio", "Rent1BD", "Rent2BDS", "Rent3BDS", "Rent4BDS", "Rent5BDS", "Images"])
    writer.writeheader()
    last_link = 0
    cleaned_row = {}
    images = ""
    i = 0
    for row in reader:
        link = row["link-href"]
        if last_link == 0:
            cleaned_row["id"] = i + 1
            cleaned_row["ScrapperOrder"] = row['web-scraper-order']
            cleaned_row["Link"] = row["link-href"]
            address = row["address"].split("|")
            cleaned_row["ApartmentName"] = address[0]
            cleaned_row["Address"] = address[1]
            monthly_rent = row["monthly_rent"] + row["unit_type"]
            rent = monthly_rent.split("$")[1:]
            cleaned_row["RentStudio"] = 0
            cleaned_row["Rent1BD"] = 0
            cleaned_row["Rent2BDS"] = 0
            cleaned_row["Rent3BDS"] = 0
            cleaned_row["Rent4BDS"] = 0
            cleaned_row["Rent5BDS"] = 0
            for one_type in rent:
                if one_type.__contains__("Studio"):
                    cleaned_row["RentStudio"] = one_type.split("+")[0]
                if one_type.__contains__("1 bd"):
                    cleaned_row["Rent1BD"] = one_type.split("+")[0]
                if one_type.__contains__("2 bd"):
                    cleaned_row["Rent2BDS"] = one_type.split("+")[0]
                if one_type.__contains__("3 bd"):
                    cleaned_row["Rent3BDS"] = one_type.split("+")[0]
                if one_type.__contains__("4 bd"):
                    cleaned_row["Rent4BDS"] = one_type.split("+")[0]
                if one_type.__contains__("5 bd"):
                    cleaned_row["Rent5BDS"] = one_type.split("+")[0]
            images = row["images-src"] 
        elif link != last_link:
            i += 1
            cleaned_row["Images"] = images
            writer.writerow(cleaned_row)
            cleaned_row = {}
            cleaned_row["id"] = i + 1
            cleaned_row["ScrapperOrder"] = row['web-scraper-order']
            cleaned_row["Link"] = row["link-href"]
            address = row["address"].split("|")
            cleaned_row["ApartmentName"] = address[0]
            cleaned_row["Address"] = address[1]
            monthly_rent = row["monthly_rent"] + row["unit_type"]
            rent = monthly_rent.split("$")[1:]
            cleaned_row["RentStudio"] = 0
            cleaned_row["Rent1BD"] = 0
            cleaned_row["Rent2BDS"] = 0
            cleaned_row["Rent3BDS"] = 0
            cleaned_row["Rent4BDS"] = 0
            cleaned_row["Rent5BDS"] = 0
            for one_type in rent:
                if one_type.__contains__("Studio"):
                    cleaned_row["RentStudio"] = one_type.split("+")[0]
                if one_type.__contains__("1 bd"):
                    cleaned_row["Rent1BD"] = one_type.split("+")[0]
                if one_type.__contains__("2 bd"):
                    cleaned_row["Rent2BDS"] = one_type.split("+")[0]
                if one_type.__contains__("3 bd"):
                    cleaned_row["Rent3BDS"] = one_type.split("+")[0]
                if one_type.__contains__("4 bd"):
                    cleaned_row["Rent4BDS"] = one_type.split("+")[0]
                if one_type.__contains__("5 bd"):
                    cleaned_row["Rent5BDS"] = one_type.split("+")[0]
            images = row["images-src"] 
        else:
            images = images + "," + row["images-src"] 
        last_link = link
            
            
            
        