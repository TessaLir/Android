package ru.vetukov.onyma.test.onymamobile.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.vetukov.onyma.test.onymamobile.query.AuthQuery;
import ru.vetukov.onyma.test.onymamobile.query.BodyHardQuery;
import ru.vetukov.onyma.test.onymamobile.query.BodyLightQuery;
import ru.vetukov.onyma.test.onymamobile.query.LightQuery;
import ru.vetukov.onyma.test.onymamobile.result.ContactsExample;
import ru.vetukov.onyma.test.onymamobile.result.ContractExample;
import ru.vetukov.onyma.test.onymamobile.result.GroupExample;
import ru.vetukov.onyma.test.onymamobile.result.PartyExample;
import ru.vetukov.onyma.test.onymamobile.result.ResultLiteExample;

public interface OnymaService {

    @POST("/onyma/system/api/jsonrpc")
    Call<ResultLiteExample> getPartyAuth (@Body AuthQuery query );

    @POST("/onyma/system/api/jsonrpc")
    Call<ResultLiteExample> getPartyReAuth (@Body LightQuery query );

    @POST("/onyma/system/api/jsonrpc")
    Call<ResultLiteExample> getLightQuery (@Body BodyLightQuery query );

    @POST("/onyma/system/api/jsonrpc")
    Call<ContractExample> getContract (@Body BodyHardQuery query );

    @POST("/onyma/system/api/jsonrpc")
    Call<PartyExample> getParty (@Body BodyHardQuery query );

    @POST("/onyma/system/api/jsonrpc")
    Call<GroupExample> getGroupName (@Body BodyHardQuery query );

    @POST("/onyma/system/api/jsonrpc")
    Call<ContactsExample> getContacts (@Body BodyLightQuery query );

}
