function ConfirmDelete(surname,name)
{
    var x = confirm("Are you sure you want to delete "+surname+" "+name+" ?");
    if (x)
        return true;
    else
        return false;
}

function ConfirmDeleteCourse(code,name)
{
    var x = confirm("Are you sure you want to delete the course whit code: "+code+" and name: "+name+" ?");
    if (x)
        return true;
    else
        return false;
}

function ConfirmDeleteStudyCourse(code,name)
{
    var x = confirm("Are you sure you want to delete the study course whit code: "+code+" and name: "+name+" ?");
    if (x)
        return true;
    else
        return false;
}


