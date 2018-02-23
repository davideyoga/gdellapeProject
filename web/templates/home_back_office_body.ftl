<div class="container">
    <p><#if message??>${message}<#else></#if></p>

    <div class="w3ls-heading">
        <h1>Home BackOffice</h1>
        <h3>Benvenuto ${user.name}</h3>
    </div>

    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course">
            <div class="w3ls-heading personal2">
                <h3>Sezione Utenti</h3>
            </div>
            <div class="">
                <#list services as service>
                    <#if service.name == 'createUser'>
                        <#assign createUser= true>
                    </#if>
                </#list>
                <#if createUser>
                    <a href="/CreateUser">
                        <section class="flat">
                            <button>Crea nuovo utente</button>
                        </section>
                    </a>
                </#if>
                <#list services as service>
                    <#if service.name == 'viewUser'>
                        <#assign viewUser= true>
                    </#if>
                </#list>
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

    <div class="clearfix"> </div>

    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course">
            <div class="w3ls-heading personal">
                <h3>Sezione percorsi di laurea</h3>
            </div>
            <div class="">
                <#list services as service>
                    <#if service.name == 'createStudyCourse'>
                        <#assign createStudyCourse= true>
                    </#if>
                </#list>
                <#if createStudyCourse>
                <a href="/createStudyCourse">
                    <section class="flat">
                        <button>Crea Percorso di laurea</button>
                    </section>
                </a>
                </#if>

                <#list services as service>
                    <#if service.name == 'viewStudyCourse'>
                        <#assign viewStudyCourse= true>
                    </#if>
                </#list>
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

    <div class="clearfix"> </div>

    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course">
            <div class="w3ls-heading personal">
                <h3>Sezione Insegnamenti</h3>
            </div>
            <div class="">
                <#list services as service>
                    <#if service.name == 'createCourse'>
                        <#assign createCourse= true>
                    </#if>
                </#list>
                <#if createCourse>
                <a href="/createCourse">
                    <section class="flat">
                        <button>Crea insegnamento</button>
                    </section>
                </a>
                </#if>
                <#list services as service>
                    <#if service.name == 'modCourse'>
                        <#assign modCourse= true>
                    </#if>
                </#list>
                <#if modCourse>
                <a href="/ListCourse">
                    <section class="flat">
                        <button>Lista insegnamenti</button>
                    </section>
                </a>
                </#if>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 col-xs-12 fa-border-course">
            <div class="w3ls-heading personal">
                <h3>Sezione Gruppi</h3>
            </div>
            <div class="">
                <#list services as service>
                    <#if service.name == 'createGroup'>
                        <#assign createGroup = true>
                    </#if>
                </#list>
                <#if createGroup>
                <a href="/createGroups">
                    <section class="flat">
                        <button>Crea Gruppo</button>
                    </section>
                </a>
                </#if>
                <#list services as service>
                    <#if service.name == 'modGroups'>
                        <#assign modGroups = true>
                    </#if>
                </#list>
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
    <#list services as service>
        <#if service.name == 'logView'>
            <#assign logView = true>
        </#if>
    </#list>
    <#if logView>
        <div class="row">
            <div class="col-md-12 col-xs-12 fa-border-course">
                <div class="w3ls-heading personal">
                    <h3>Log</h3>
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