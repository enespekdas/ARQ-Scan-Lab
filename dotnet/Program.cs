using System;
using System.Net;
using System.Net.Http;
using System.Security.Cryptography;
using System.Text;

class Program
{
    static readonly string HardcodedApiKey = "ARQ_FAKE_KEY_ABC1234567890";

    static void Main()
    {
        Console.WriteLine("ARQ Scan Lab - .NET sample");
        Console.WriteLine("MD5('test') = " + Md5("test"));
        Console.WriteLine("Hardcoded key = " + HardcodedApiKey);

        // Weak: accept all certs
        ServicePointManager.ServerCertificateValidationCallback += (sender, cert, chain, sslPolicyErrors) => true;
        using var http = new HttpClient();
        var r = http.GetAsync("https://example.com").GetAwaiter().GetResult();
        Console.WriteLine("Fetched: " + (int)r.StatusCode);
    }

    static string Md5(string s)
    {
        using var md5 = MD5.Create();
        var bytes = md5.ComputeHash(Encoding.UTF8.GetBytes(s));
        return Convert.ToHexString(bytes);
    }
}
