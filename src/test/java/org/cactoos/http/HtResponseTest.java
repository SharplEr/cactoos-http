/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.http;

import java.io.IOException;
import org.cactoos.text.JoinedText;
import org.cactoos.text.TextOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.http.FtRemote;
import org.takes.tk.TkText;

/**
 * Test case for {@link HtResponse}.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class HtResponseTest {

    @Test
    public void worksFine() throws IOException {
        new FtRemote(new TkText("Hello, world!")).exec(
            home -> MatcherAssert.assertThat(
                new TextOf(
                    new HtResponse(
                        new HtWire(home),
                        new JoinedText(
                            "\r\n",
                            "GET / HTTP/1.1",
                            String.format("Host:%s", home.getHost())
                        ).asString()
                    )
                ).asString(),
                Matchers.containsString("HTTP/1.1 200 ")
            )
        );
    }

    @Test
    public void worksFineByUri() throws IOException {
        new FtRemote(new TkText("Hello, dude!")).exec(
            home -> MatcherAssert.assertThat(
                new TextOf(
                    new HtResponse(home)
                ).asString(),
                Matchers.containsString("HTTP/1.1 200 OK")
            )
        );
    }

}
