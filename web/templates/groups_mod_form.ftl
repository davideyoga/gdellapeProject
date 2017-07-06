<div class="profile_form">
    <h1><a href="profile">PortaleDell'Universita' </a></h1>
    <div class="profile-button">

        <p><#if message??>${message}<#else></#if></p>


        <h2>Mod ${groups.email}</h2>
        <form method="POST" action="AdmModGroups">
            <div class="col-md-6">

                <div class="groups-form-name">
                    email
                    <input type="text" value= "<#if groups.name??>${groups.name}<#else></#if>" name="name" >
                    <i class="fa fa-lock"></i>
                </div>

                <div class="groups-form-description">
                    Password
                    <input type="text" value= "<#if groups.description??>${groups.description}<#else></#if>" name="description" >
                    <i class="fa fa-lock"></i>
                </div>


            <#list listService as service>

                <input type="checkbox" name="${service.name}" value="${service.name}" <#if listGroupsService?seq_contains(service) >checked<#else></#if> > ${service.name} <br>

            </#list>

            </div>
            <div class="col-md-6 login-do">
                <label class="hvr-shutter-in-horizontal login-sub">
                    <input type="submit" value="Aggiorna Gruppo">
                </label>
            </div>

            <div class="clearfix"> </div>
        </form>
    </div>
</div>