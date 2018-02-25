function ConfirmDelete(surname,name)
{
    var x = confirm("Are you sure you want to delete "+surname+" "+name+" ?");
    if (x)
        return true;
    else
        return false;
}