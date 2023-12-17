import { Storage } from "@google-cloud/storage";
import "dotenv/config";


const storage = new Storage({
    projectId: "petme-406008",
    credentials: {
      type: process.env.CREDENTIAL_TYPE,
      project_id: process.env.CREDENTIAL_PROJECT_ID,
      private_key_id: process.env.CREDENTIAL_PRIVATE_KEY_ID,
      private_key: process.env.CREDENTIAL_PRIVATE_KEY.replace(/\\n/g, '\n'),
      client_email: process.env.CREDENTIAL_CLIENT_EMAIL,
      client_id: process.env.CREDENTIAL_CLIENT_ID,
      auth_uri: process.env.CREDENTIAL_AUTH_URI,
      token_uri: process.env.CREDENTIAL_TOKEN_URI,
      auth_provider_x509_cert_url:
        process.env.CREDENTIAL_AUTH_PROVIDER_X509_CERT_URL,
      client_x509_cert_url: process.env.CREDENTIAL_CLIENT_X509_CERT_URL,
    },
  });
const bucket = storage.bucket("petmebucket");
const folderName = "user_data";
export { bucket, folderName };
