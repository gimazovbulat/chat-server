package myApp.protocol;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import myApp.protocol.commands.*;
import myApp.protocol.help.HelpPayload;
import myApp.protocol.help.HelpRequestPayload;
import myApp.protocol.loginLogout.LoginPayload;
import myApp.protocol.loginLogout.LoginStatusPayload;
import myApp.protocol.loginLogout.LogoutPayload;
import myApp.protocol.messages.MessagePayload;
import myApp.protocol.messages.MessagesResponsePayload;
import myApp.protocol.messages.SingleMessagePayload;
import myApp.protocol.products.ProductsResponsePayload;
import myApp.protocol.status.FailRespPayload;
import myApp.protocol.status.SuccessRespPayload;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CommandPayload.class, name = "command"),
        @JsonSubTypes.Type(value = LoginPayload.class, name = "login"),
        @JsonSubTypes.Type(value = LogoutPayload.class, name = "logout"),
        @JsonSubTypes.Type(value = MessagePayload.class, name = "message"),
        @JsonSubTypes.Type(value = MessagesResponsePayload.class, name = "messageResponse"),
        @JsonSubTypes.Type(value = SingleMessagePayload.class, name = "singleMessage"),
        @JsonSubTypes.Type(value = LoginStatusPayload.class, name = "loginStatus"),
        @JsonSubTypes.Type(value = HelpPayload.class, name = "helpResponse"),
        @JsonSubTypes.Type(value = FailRespPayload.class, name = "failRespPayload"),
        @JsonSubTypes.Type(value = SuccessRespPayload.class, name = "successRespPayload"),
        @JsonSubTypes.Type(value = CommandListPayload.class, name = "commandListPayload"),
        @JsonSubTypes.Type(value = CommandPurchasePayload.class, name = "commandPurchasePayload"),
        @JsonSubTypes.Type(value = CommandRemoveProdPayload.class, name = "commandRemoveProdPayload"),
        @JsonSubTypes.Type(value = CommandAddProdPayload.class, name = "commandAddProdPayload"),
        @JsonSubTypes.Type(value = ProductsResponsePayload.class, name = "productsResponsePayload"),
        @JsonSubTypes.Type(value = HelpRequestPayload.class, name = "helpRequest")
})
public abstract class AbstractPayload {

}