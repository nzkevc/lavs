package uoa.lavs.mainframe.simulator;

import java.io.IOException;
import java.util.HashMap;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.index.IndexOptions;
import org.dizitart.no2.index.IndexType;
import org.dizitart.no2.mvstore.MVStoreModule;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MessageErrorStatus;
import uoa.lavs.mainframe.Request;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.All;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;
import uoa.lavs.mainframe.messages.customer.LoadCustomerAddress;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmail;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmployer;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumber;
import uoa.lavs.mainframe.messages.customer.LoadCustomerUpdateStatus;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerAddress;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmail;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmployer;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerPhoneNumber;
import uoa.lavs.mainframe.simulator.nitrite.FindCustomerAdvancedProcessor;
import uoa.lavs.mainframe.simulator.nitrite.FindCustomerProcessor;
import uoa.lavs.mainframe.simulator.nitrite.FindLoanProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadCustomerAddressesProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadCustomerEmailsProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadCustomerItemProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadCustomerNoteProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadCustomerPhoneNumbersProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadCustomerProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadCustomerUpdateStatusProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadLoanCoborrowersProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadLoanPaymentsProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadLoanProcessor;
import uoa.lavs.mainframe.simulator.nitrite.LoadLoanSummaryProcessor;
import uoa.lavs.mainframe.simulator.nitrite.UpdateCustomerItemProcessor;
import uoa.lavs.mainframe.simulator.nitrite.UpdateCustomerNoteProcessor;
import uoa.lavs.mainframe.simulator.nitrite.UpdateCustomerProcessor;
import uoa.lavs.mainframe.simulator.nitrite.UpdateLoanCoborrowerProcessor;
import uoa.lavs.mainframe.simulator.nitrite.UpdateLoanProcessor;
import uoa.lavs.mainframe.simulator.nitrite.UpdateLoanStatusProcessor;

public class NitriteConnection implements Connection {
    private final Nitrite database;
    private final HashMap<Integer, MessageProcessor> messageProcessors = new HashMap<>();
    private long transactionId = 0;

    public NitriteConnection(String dataPath) {
        MVStoreModule storeModule = MVStoreModule.withConfig()
                .filePath(dataPath)
                .compress(true)
                .build();

        database = Nitrite.builder()
                .loadModule(storeModule)
                .openOrCreate();
        initializeMessageProcessors();
        initializeIndices();
    }

    public NitriteConnection(Nitrite database) {
        this.database = database;
        initializeMessageProcessors();
        initializeIndices();
    }

    public NitriteConnection() {
        this(Nitrite.builder().openOrCreate());
    }

    private void initializeIndices() {
        database.getCollection(Internal.CUSTOMERS_COLLECTION)
                .createIndex(IndexOptions.indexOptions(IndexType.FULL_TEXT), LoadCustomer.Fields.NAME);
    }

    private void initializeMessageProcessors() {
        messageProcessors.put(All.FindCustomer, new FindCustomerProcessor(database));
        messageProcessors.put(All.FindCustomerAdvanced, new FindCustomerAdvancedProcessor(database));
        messageProcessors.put(All.FindLoan, new FindLoanProcessor(database));
        messageProcessors.put(All.LoadCustomer, new LoadCustomerProcessor(database));
        messageProcessors.put(All.LoadCustomerAddress,
                new LoadCustomerItemProcessor(database,
                        "addresses",
                        MessageErrorStatus.CUSTOMER_ADDRESS_NOT_FOUND,
                        LoadCustomerAddress.Fields.OUTPUT));
        messageProcessors.put(All.LoadCustomerAddresses, new LoadCustomerAddressesProcessor(database));
        messageProcessors.put(All.LoadCustomerEmail,
                new LoadCustomerItemProcessor(database,
                        "emails",
                        MessageErrorStatus.CUSTOMER_EMAIL_NOT_FOUND,
                        LoadCustomerEmail.Fields.OUTPUT));
        messageProcessors.put(All.LoadCustomerEmails, new LoadCustomerEmailsProcessor(database));
        messageProcessors.put(All.LoadCustomerEmployer,
                new LoadCustomerItemProcessor(database,
                        "employers",
                        MessageErrorStatus.CUSTOMER_EMPLOYER_NOT_FOUND,
                        LoadCustomerEmployer.Fields.OUTPUT));
        messageProcessors.put(All.LoadCustomerPhoneNumber,
                new LoadCustomerItemProcessor(database,
                        "phoneNumbers",
                        MessageErrorStatus.CUSTOMER_PHONE_NUMBER_NOT_FOUND,
                        LoadCustomerPhoneNumber.Fields.OUTPUT));
        messageProcessors.put(All.LoadCustomerPhoneNumbers, new LoadCustomerPhoneNumbersProcessor(database));
        messageProcessors.put(All.LoadCustomerNote, new LoadCustomerNoteProcessor(database));
        messageProcessors.put(All.LoadCustomerUpdateStatus, new LoadCustomerUpdateStatusProcessor(database));
        messageProcessors.put(All.LoadLoan, new LoadLoanProcessor(database));
        messageProcessors.put(All.LoadLoanCoborrowers, new LoadLoanCoborrowersProcessor(database));
        messageProcessors.put(All.LoadLoanPayments, new LoadLoanPaymentsProcessor(database));
        messageProcessors.put(All.LoadLoanSummary, new LoadLoanSummaryProcessor(database));
        messageProcessors.put(All.UpdateCustomer, new UpdateCustomerProcessor(database));
        messageProcessors.put(All.UpdateCustomerAddress,
                new UpdateCustomerItemProcessor(database,
                        "addresses",
                        UpdateCustomerAddress.Fields.INPUT,
                        UpdateCustomerAddress.Fields.OUTPUT,
                        LoadCustomerUpdateStatus.Fields.LAST_ADDRESS_CHANGE));
        messageProcessors.put(All.UpdateCustomerEmail,
                new UpdateCustomerItemProcessor(database,
                        "emails",
                        UpdateCustomerEmail.Fields.INPUT,
                        UpdateCustomerEmail.Fields.OUTPUT,
                        LoadCustomerUpdateStatus.Fields.LAST_EMAIL_CHANGE));
        messageProcessors.put(All.UpdateCustomerEmployer,
                new UpdateCustomerItemProcessor(database,
                        "employers",
                        UpdateCustomerEmployer.Fields.INPUT,
                        UpdateCustomerEmployer.Fields.OUTPUT,
                        LoadCustomerUpdateStatus.Fields.LAST_DETAILS_CHANGE));
        messageProcessors.put(All.UpdateCustomerNote, new UpdateCustomerNoteProcessor(database));
        messageProcessors.put(All.UpdateCustomerPhoneNumber,
                new UpdateCustomerItemProcessor(database,
                        "phoneNumbers",
                        UpdateCustomerPhoneNumber.Fields.INPUT,
                        UpdateCustomerPhoneNumber.Fields.OUTPUT,
                        LoadCustomerUpdateStatus.Fields.LAST_PHONE_NUMBER_CHANGE));
        messageProcessors.put(All.UpdateLoan, new UpdateLoanProcessor(database));
        messageProcessors.put(All.UpdateLoanCoborrower, new UpdateLoanCoborrowerProcessor(database));
        messageProcessors.put(All.UpdateLoanStatus, new UpdateLoanStatusProcessor(database));
    }

    @Override
    public Response send(Request request) {
        MessageProcessor processor = messageProcessors.get(request.getRequestType());
        if (processor == null) {
            return MessageErrorStatus.UNKNOWN_MESSAGE.generateEmptyResponse(++transactionId);
        }

        try {
            return processor.process(request, ++transactionId);
        } catch (Exception ex) {
            return new Response(
                    new Status(MessageErrorStatus.INTERNAL_FAILURE.getCode(), ex.getMessage(), transactionId),
                    new HashMap<>());
        }
    }

    @Override
    public void close() throws IOException {
        database.close();
    }

    public class Internal {
        public static final String CUSTOMERS_COLLECTION = "customers";
        public static final String IDS_COLLECTION = "ids";
        public static final String LOANS_COLLECTION = "loans";

        public static final String NEXT_LOAN_ID = "nextLoanId";

        public static final String ITEM_ADDRESSES = "addresses";
        public static final String ITEM_COBORROWERS = "coborrowers";
        public static final String ITEM_EMAILS = "emails";
        public static final String ITEM_EMPLOYERS = "employers";
        public static final String ITEM_NOTES = "notes";
        public static final String ITEM_PHONE_NUMBERS = "phoneNumbers";
    }
}
