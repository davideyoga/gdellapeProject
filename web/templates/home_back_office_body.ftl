<div class="container">
    <div class="center-block">
        <div class="w3ls-heading page-header">
            <h1>Home BackOffice</h1>
            <h3>Benvenuto ${user.name}</h3>
        </div>
    </div>
</div>

<div class="container">
    <div>
        <#if message??>
            <div class="jumbotron">
                ${message}
            </div>
        <#else>
        </#if>
    </div>
</div>

<div class="container">

    <#list services as service>
        <#if service.name == 'createUser'>
            <#assign createUser= true>
        </#if>
        <#if service.name == 'viewUser'>
            <#assign viewUser= true>
        </#if>
        <#if service.name == 'createStudyCourse'>
            <#assign createStudyCourse= true>
        </#if>
        <#if service.name == 'viewStudyCourse'>
            <#assign viewStudyCourse= true>
        </#if>
        <#if service.name == 'createCourse'>
            <#assign createCourse= true>
        </#if>
        <#if service.name == 'modCourse'>
            <#assign modCourse= true>
        </#if>
        <#if service.name == 'updateCourse'>
            <#assign updateCourse= true>
        </#if>
        <#if service.name == 'createGroup'>
            <#assign createGroup = true>
        </#if>
        <#if service.name == 'modGroups'>
            <#assign modGroups = true>
        </#if>
        <#if service.name == 'logView'>
            <#assign logView = true>
        </#if>
    </#list>

    <#if createUser?? || viewUser??>
    <div class="row">
        <div class="col-md-12 col-xs-12  well">
            <#--<div class="w3ls-heading personal2">-->
            <div class="page-header text-center">
                <h2>Sezione Utenti</h2>
            </div>
            <div class="">

                <#if createUser>
                    <a href="/CreateUser">
                        <section class="flat">
                            <button>Crea nuovo utente</button>
                        </section>
                    </a>
                </#if>

                <#if viewUser>
                <a href="/admGetListUser">
                    <section class="flat">
                        <button>Lista utenti</button>
                    </section>
                </a>
                </#if>
            </div>
        </div>
    </div>
    </#if>

    <div class="clearfix"> </div>

    <#if createStudyCourse?? || viewStudyCourse??>
    <div class="row">
        <div class="col-md-12 col-xs-12 well">
            <div class="page-header text-center">
                <h2>Sezione percorsi di laurea</h2>
            </div>
            <div class="">
                <#if createStudyCourse>
                <a href="/createStudyCourse">
                    <section class="flat">
                        <button>Crea Percorso di laurea</button>
                    </section>
                </a>
                </#if>

                <#if viewStudyCourse>
                <a href="/admGetListStudyCourse">
                    <section class="flat">
                        <button>Lista corsi di laurea</button>
                    </section>
                </a>
                </#if>
            </div>
        </div>
    </div>
    </#if>

    <div class="clearfix"> </div>

    <#if createCourse?? || modCourse?? || updateCourse??>
    <div class="row">
        <div class="col-md-12 col-xs-12 well">
            <div class="page-header text-center">
                <h2>Sezione Insegnamenti</h2>
            </div>
            <div class="">

                <#if createCourse>
                <a href="/createCourse">
                    <section class="flat">
                        <button>Crea insegnamento</button>
                    </section>
                </a>
                </#if>

                <#if modCourse>
                <a href="/ListCourse">
                    <section class="flat">
                        <button>Lista insegnamenti</button>
                    </section>
                </a>
                </#if>

                <#if updateCourse>
                <a href="/UpdateCourse">
                    <section class="flat">
                        <button>Aggiorna insegnamenti</button>
                    </section>
                </a>
                </#if>
            </div>
        </div>
    </div>
    </#if>

    <#if createGroup?? || modGroups??>
    <div class="row">
        <div class="col-md-12 col-xs-12 well">
            <div class="page-header text-center">
                <h2>Sezione Gruppi</h2>
            </div>
            <div class="">

                <#if createGroup>
                <a href="/createGroups">
                    <section class="flat">
                        <button>Crea Gruppo</button>
                    </section>
                </a>
                </#if>

                <#if modGroups>
                <a href="/AdmGetListGroups">
                    <section class="flat">
                        <button>Lista Gruppi</button>
                    </section>
                </a>
                </#if>
            </div>
        </div>
    </div>
    </#if>


    <#if logView??>
        <div class="row">
            <div class="col-md-12 col-xs-12 well">
                <div class="page-header text-center">
                    <h2>Log</h2>
                </div>
                <div class="">

                    <a href="/GetAllLog">
                        <section class="flat">
                            <button>Visualizza Log</button>
                        </section>
                    </a>
                </div>
            </div>
        </div>
    </#if>

</div>