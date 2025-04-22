
### 🔹 **Experiment No. 1**  
**Title:** Installation of Docker Container, Node.js, Hyperledger Fabric, and Ethereum Network

**Aim:**  
To install Docker, Node.js, Hyperledger Fabric, and Ethereum, and configure the environment for blockchain development.

**Procedure:**  
1. Install Docker and Docker Compose.
2. Install Node.js, Express.js, and nodemon.
3. Create a sample Node.js REST API project.
4. Write a Dockerfile and build an image using `docker build`.
5. Run the Docker container using `docker run`.
6. Install Hyperledger Fabric samples via `install-fabric.sh`.
7. Deploy the Fabric test network using `./network.sh up`.
8. Install Geth, TestRPC, and Truffle for Ethereum.

**Output:**  
- Docker container runs the Node.js app.
- Hyperledger Fabric test network is up.
- Ethereum node via Geth initialized.

**Result:**  
Installation and configuration of Docker, Node.js, Hyperledger Fabric, and Ethereum were successful.

---

### 🔹 **Experiment No. 2**  
**Title:** Understand the Basics of Bitcoin Transactions, Mining, and Wallet Management

**Aim:**  
To understand Bitcoin transactions, mining, and wallet management using testnet tools and blockchain explorers.

**Procedure:**  
1. Install Electrum (testnet mode).
2. Create a new wallet and generate public/private keys.
3. Receive test BTC via testnet faucet.
4. Send BTC and confirm via TXID.
5. Simulate mining using an SHA-256 calculator.

**Sample Output:**  
- Transaction ID: `b6f1...a45d`
- Amount Sent: `0.002 BTC`
- Confirmations: `3+`

**Result:**  
Successfully managed a Bitcoin wallet, performed transactions, and simulated mining using testnet.

---

### 🔹 **Experiment No. 3**  
**Title:** Implement ECC Key Generation Algorithm in Java

**Aim:**  
To implement Elliptic Curve Cryptography (ECC) for secure key generation, encryption, and decryption in Java.

**Procedure:**  
1. Define the elliptic curve: `y² = x³ + ax + b (mod p)`.
2. Select generator point G(x, y).
3. Generate public and private keys using scalar multiplication.
4. Encrypt a message using ECC.
5. Decrypt the ciphertext using ECC.

**Program:**  
📌 You already pasted the Java ECC program earlier (we've formatted it nicely).

**Sample Output:**  
```
Elliptic curve: y^2 = x^3 + 2x + 3 mod 97
Generator point set: G(3, 6)
Public Key: (80, 10)
Ciphertext C1: (..., ...)
Ciphertext C2: (..., ...)
Decrypted Message: (5, 1)
```

**Result:**  
The ECC algorithm was successfully implemented for secure key generation and message encryption/decryption.

Perfect! Here are the formatted entries for **Experiments 4 to 7** from your lab manual, including **aim, procedure, program snippets, commands**, and **expected output/result** where applicable.

---

## 🔹 **Experiment No. 4**  
**Title:** Explore Web3j, a Java Library for Ethereum Interaction

**Aim:**  
To use Web3j to interact with an Ethereum blockchain — retrieve client version, block number, and account balance.

**Procedure:**
1. Install Web3j in your Java project using Maven or Gradle.
2. Connect to Ethereum node using Infura.
3. Retrieve the latest block number.
4. Fetch ETH balance of an account.

**Required Dependency (Maven):**
```xml
<dependency>
  <groupId>org.web3j</groupId>
  <artifactId>core</artifactId>
  <version>4.8.7</version>
</dependency>
```

**Program:**
```java
String infuraUrl = "https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID";
Web3j web3 = Web3j.build(new HttpService(infuraUrl));

Web3ClientVersion clientVersion = web3.web3ClientVersion().send();
System.out.println("Ethereum Client Version: " + clientVersion.getWeb3ClientVersion());

EthBlockNumber blockNumber = web3.ethBlockNumber().send();
System.out.println("Latest Block Number: " + blockNumber.getBlockNumber());

String address = "0x742d35Cc6634C0532925a3b844Bc454e4438f44e";
EthGetBalance ethGetBalance = web3.ethGetBalance(
    address, DefaultBlockParameterName.LATEST).send();
BigDecimal ethBalance = Convert.fromWei(new BigDecimal(ethGetBalance.getBalance()), Convert.Unit.ETHER);
System.out.println("ETH Balance: " + ethBalance + " ETH");
```

**Expected Output:**
```
Ethereum Client Version: Geth/v1.10...
Latest Block Number: 19045678
ETH Balance: 274.89 ETH
```

**Result:**  
Successfully interacted with the Ethereum blockchain using Web3j and retrieved key information.

---

## 🔹 **Experiment No. 5**  
**Title:** Interact with a Blockchain Network by Creating a Test App

**Aim:**  
To interact with a blockchain network and execute transactions by creating a test app to validate network rules.

**Procedure:**
1. Install prerequisites:
   ```bash
   sudo apt install docker-compose git nodejs npm
   ```

2. Download and set up Hyperledger Fabric test network:
   ```bash
   curl -sSL https://bit.ly/2ysbOFE | bash -s
   cd fabric-samples/test-network
   ./network.sh up
   ```

3. Use Composer to generate the business network:
   ```bash
   yo hyperledger-composer
   ```

4. Create model file for assets and participants.

5. Generate BNA:
   ```bash
   composer archive create --sourceType dir --sourceName .
   ```

6. Deploy and start the network:
   ```bash
   composer network install --archiveFile <filename>.bna --card PeerAdmin@hlfv1
   composer network start --networkName <name> --networkVersion 0.0.1 --networkAdmin admin --networkAdminEnrollSecret adminpw --card PeerAdmin@hlfv1 --file <admin.card>
   composer card import --file <admin.card>
   ```

7. Interact via REST API:
   ```bash
   composer-rest-server
   ```

**Expected Output:**
- REST API server running at `http://localhost:3000`
- Network ping: `composer network ping --card admin@<networkName>` shows `success`

**Result:**  
Successfully created and deployed a blockchain business network and verified transactions through a REST interface.

---

## 🔹 **Experiment No. 6**  
**Title:** Create and Deploy Blockchain Network Using Hyperledger Fabric SDK for Java

**Aim:**  
To create a blockchain network using Fabric SDK for Java and perform invoke/query operations.

**Procedure:**
1. Clone the sample repository:
   ```bash
   git clone https://github.com/IBM/blockchain-application-using-fabric-java-sdk
   ```

2. Navigate to the `network` folder and run:
   ```bash
   chmod +x build.sh
   ./build.sh
   ```

3. Navigate to `java/` and build:
   ```bash
   mvn install
   ```

4. Copy the JAR to the `network_resources` folder.

5. Run the following Java classes:
   ```bash
   java -cp blockchain-client.jar org.example.network.CreateChannel
   java -cp blockchain-client.jar org.example.network.DeployInstantiateChaincode
   java -cp blockchain-client.jar org.example.user.RegisterEnrollUser
   java -cp blockchain-client.jar org.example.chaincode.invocation.InvokeChaincode
   java -cp blockchain-client.jar org.example.chaincode.invocation.QueryChaincode
   ```

**Expected Output:**
```
INFO: Channel created mychannel
INFO: fabcar - Chaincode deployment SUCCESS
INFO: {"make":"Chevy","model":"Volt","colour":"Red","owner":"Nick"}
```

**Result:**  
Blockchain network successfully deployed and tested using Fabric Java SDK.

---

## 🔹 **Experiment No. 7**  
**Title:** Deploy an Asset-Transfer App Using Hyperledger Fabric

**Aim:**  
To deploy a basic asset-transfer smart contract on Hyperledger Fabric and interact via a JavaScript app.

**Procedure:**
1. Launch the test network:
   ```bash
   cd fabric-samples/test-network
   ./network.sh up createChannel -ca
   ```

2. Deploy chaincode:
   ```bash
   ./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-javascript/ -ccl javascript
   ```

3. Setup JavaScript App:
   ```bash
   cd ../asset-transfer-basic/application-javascript
   npm install
   ```

4. Run app and initialize ledger:
   ```bash
   node app.js
   ```

**Expected Output (Console Logs):**
```
--> Submit Transaction: InitLedger
*** Result: committed
--> Evaluate Transaction: GetAllAssets
[
  { ID: 'asset1', Color: 'blue', Size: 5, Owner: 'Tomoko', AppraisedValue: 300 },
  ...
]
```

**Result:**  
The asset-transfer app was deployed and successfully interacted with a blockchain network using JavaScript SDK.


---

## 🔹 **Experiment No. 8**  
**Title:** Fitness Club Rewards Using Hyperledger Fabric

**Aim:**  
To build a web application that tracks fitness club rewards using a smart contract on Hyperledger Fabric.

**Procedure:**

1. **Set up Fabric Network**  
   - Use `fabric-samples/test-network` to deploy using:
     ```bash
     ./network.sh up createChannel -ca
     ```

2. **Define Smart Contract (Go):**

```go
type RewardsContract struct {
    contractapi.Contract
}
type Reward struct {
    MemberID string `json:"memberID"`
    Points   int    `json:"points"`
}

func (rc *RewardsContract) IssueReward(ctx contractapi.TransactionContextInterface, memberID string, points int) error {
    reward := Reward{MemberID: memberID, Points: points}
    rewardJSON, _ := json.Marshal(reward)
    return ctx.GetStub().PutState(memberID, rewardJSON)
}

func (rc *RewardsContract) GetReward(ctx contractapi.TransactionContextInterface, memberID string) (*Reward, error) {
    rewardJSON, _ := ctx.GetStub().GetState(memberID)
    var reward Reward
    json.Unmarshal(rewardJSON, &reward)
    return &reward, nil
}
```

3. **Develop Frontend:**
   - Use React or plain HTML/JS to connect with the Fabric network via REST APIs.

4. **Run REST Server:**
   ```bash
   composer-rest-server
   ```

**Expected Output:**  
- Console logs showing reward issuance and retrieval.
- Web UI to input `MemberID` and Points and retrieve rewards.

**Result:**  
Successfully developed a blockchain-based reward system using Hyperledger Fabric and a simple frontend.

---

## 🔹 **Experiment No. 9**  
**Title:** Create and Deploy a Simple Smart Contract on Kadena

**Aim:**  
To create and deploy a basic asset ledger smart contract using Kadena’s Pact language.

**Procedure:**

1. **Install Pact Tool:**
   ```bash
   npm install -g pact-lang-api
   ```

2. **Write Pact Contract (save as `ledger.pact`):**
```lisp
(module ledger 'admin
  (defschema asset
    asset-id:string
    owner:string)

  (deftable assets:{asset})

  (defun store-asset (id:string owner:string)
    (insert assets id {"asset-id": id, "owner": owner}))

  (defun get-asset (id:string)
    (read assets id))
)
```

3. **Deploy Locally (Devnet or Testnet):**
   ```bash
   pact -a ledger.pact -l
   ```

4. **Interact via CLI or API.**

**Expected Output:**
- Contract loads successfully.
- Function calls return asset data or success on insert.

**Result:**  
Successfully created and deployed a smart contract on the Kadena blockchain using Pact.

---

## 🔹 **Experiment No. 10**  
**Title:** Analyze the Ripple Ledger for Transaction Flow

**Aim:**  
To understand Ripple’s ledger structure, consensus, and transaction processing.

**Procedure:**

1. **Understand Transaction Structure (Example JSON):**
```json
{
  "TransactionType": "Payment",
  "Account": "rSenderAddress",
  "Destination": "rReceiverAddress",
  "Amount": "1000000",
  "Fee": "10",
  "Sequence": 100
}
```

2. **Use XRPL Explorer:**  
   - Go to: https://livenet.xrpl.org
   - Paste Transaction Hash to analyze:
     - Sender/Receiver
     - Amount/Fee
     - Confirmations

3. **Consensus in Ripple:**
   - Uses **Ripple Consensus Algorithm** (not PoW).
   - Finality in 3-5 seconds.
   - 80% validator agreement required.

4. **Analyze Ledger Record:**
```json
{
  "ledger_index": 123456,
  "tx": {
    "hash": "...",
    "Account": "...",
    "Amount": "1000000",
    "Validated": true
  }
}
```

**Expected Output:**
- Insight into fast finality and structure of ledger entries.
- Transaction data fetched using hash.

**Result:**  
Ripple’s transaction ledger was successfully analyzed, highlighting its fast processing and consensus mechanism.

---

## 🔹 **Experiment No. 11**  
**Title:** Execute Transactions on Private Contract in Quorum

**Aim:**  
To deploy and execute transactions on a Quorum private contract and observe its privacy features.

**Procedure:**

1. **Install Quorum & Tessera:**
   ```bash
   git clone https://github.com/ConsenSys/quorum
   cd quorum
   make
   ```

2. **Configure Privacy with Tessera.**  
   - Define `tm.conf` with participants’ public keys.

3. **Deploy Private Contract (example.sol):**
```solidity
pragma solidity ^0.8.0;
contract PrivateStore {
    string private secret;

    function setSecret(string memory _secret) public {
        secret = _secret;
    }

    function getSecret() public view returns (string memory) {
        return secret;
    }
}
```

4. **Compile & Deploy with Privacy Flag:**
   Use Truffle + privateFor:
   ```bash
   truffle migrate --network quorum
   ```

5. **Send Private Transactions:**
   - `privateFor` ensures only listed nodes can read the data.

**Expected Output:**
- Deployment hash.
- Only selected nodes can read the private `secret`.

**Result:**  
Successfully executed private transactions on Quorum and validated privacy through restricted visibility.

---
